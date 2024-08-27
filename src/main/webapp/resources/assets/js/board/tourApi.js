
const BASE_URL = 'https://apis.data.go.kr/B551011/PhotoGalleryService1/gallerySearchList1';
const COMMON_PARAMS = 'numOfRows=100&pageNo=1&MobileOS=ETC&MobileApp=Test';
const SERVICE_KEY = 'H5W%2FW%2F1cXUq3EEuXX%2BEERNRoB2xQDv57x1Ju%2B8%2BlZGDSZ7uc88F6%2Bdre8ee9F%2FgzkqyzsJHnVc5dQXdtnuTfmA%3D%3D';

async function fetchImage(keyword) {

    const apiUrl = `${BASE_URL}?${COMMON_PARAMS}&keyword=${encodeURIComponent(keyword)}&_type=json&serviceKey=${SERVICE_KEY}`;
    
    try {
        const response = await fetch(apiUrl);
        const data = await response.json();
        
        const images = data.response.body.items.item;
        if (!images || images.length === 0) {
            console.error(`No images found for keyword: ${keyword}`);
            return null;
        }
        
        const randomIndex = Math.floor(Math.random() * images.length);
        return images[randomIndex].galWebImageUrl;
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
