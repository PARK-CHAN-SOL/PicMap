
const BASE_URL = 'https://apis.data.go.kr/B551011/PhotoGalleryService1/gallerySearchList1';
const COMMON_PARAMS = 'numOfRows=20&pageNo=1&MobileOS=ETC&MobileApp=Test';
const SERVICE_KEY = 'H5W%2FW%2F1cXUq3EEuXX%2BEERNRoB2xQDv57x1Ju%2B8%2BlZGDSZ7uc88F6%2Bdre8ee9F%2FgzkqyzsJHnVc5dQXdtnuTfmA%3D%3D';





// IndexedDB를 열거나 생성합니다.
function openDatabase() {
    return new Promise((resolve, reject) => {
        const request = indexedDB.open("ImageCacheDB", 1);

        request.onerror = (event) => {
            console.error("Database error: " + event.target.errorCode);
            reject(event.target.errorCode);
        };

        request.onsuccess = (event) => {
            resolve(event.target.result);
        };

        request.onupgradeneeded = (event) => {
            const db = event.target.result;
            if (!db.objectStoreNames.contains("images")) {
                db.createObjectStore("images", { keyPath: "keyword" });
            }
        };
    });
}

// IndexedDB에 이미지 URL을 저장합니다.
function storeImage(db, keyword, imageUrl) {
    return new Promise((resolve, reject) => {
        const transaction = db.transaction(["images"], "readwrite");
        const store = transaction.objectStore("images");
        store.put({ keyword, imageUrl });

        transaction.oncomplete = () => {
            resolve();
        };

        transaction.onerror = (event) => {
            console.error("Transaction error: " + event.target.errorCode);
            reject(event.target.errorCode);
        };
    });
}

// IndexedDB에서 이미지 URL을 조회합니다.
function getImage(db, keyword) {
    return new Promise((resolve, reject) => {
        const transaction = db.transaction(["images"], "readonly");
        const store = transaction.objectStore("images");
        const request = store.get(keyword);

        request.onsuccess = (event) => {
            resolve(event.target.result ? event.target.result.imageUrl : null);
        };

        request.onerror = (event) => {
            console.error("Get image error: " + event.target.errorCode);
            reject(event.target.errorCode);
        };
    });
}







async function fetchImage(keyword) {

    const db = await openDatabase();
    const cachedImage = await getImage(db, keyword);
    
    if (cachedImage) {
        console.log(`Loaded image from IndexedDB for keyword: ${keyword}`);
        return cachedImage;
    }    



    const apiUrl = `${BASE_URL}?${COMMON_PARAMS}&keyword=${encodeURIComponent(keyword)}&_type=json&serviceKey=${SERVICE_KEY}`;
    
    try {
        const response = await fetch(apiUrl);
        const data = await response.json();
        
        const images = data.response.body.items.item;
        if (!images || images.length === 0) {
            console.error(`No images found for keyword: ${keyword}`);
            return null;
        }
        
        console.log(images);
        
        const randomIndex = Math.floor(Math.random() * images.length);
        return images[randomIndex].galWebImageUrl;
        
        
        // IndexedDB에 이미지 URL 저장        
        await storeImage(db, keyword, imageUrl);
        
        return imageUrl;
        
                
                
    } catch (error) {
        console.error(`Error fetching images for keyword: ${keyword}`, error);
        return null;
    }
}


async function applyImages() {

    const imageElements = document.querySelectorAll('.box-image__bg');
    
    const imagePromises = Array.from(imageElements).map(async (element) => {
        const keyword = element.getAttribute('data-keyword');
        if (keyword) {
            return fetchImage(keyword);
        }
        console.error('Keyword not found for an element.');
        return null;
    });

    const imageUrls = await Promise.all(imagePromises);

    imageElements.forEach((element, index) => {
        if (imageUrls[index]) {
            element.style.backgroundImage = `url('${imageUrls[index]}')`;
        } else {
            console.error(`Failed to set image for element at index ${index}.`);
        }
    });
}

applyImages();
