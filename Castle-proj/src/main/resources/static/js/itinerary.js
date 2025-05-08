document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('download').addEventListener('click', function () {
        const element = document.getElementById('content');
        const options = {
            filename: 'itinerary.pdf',
            image: {type: 'jpeg', quality: 0.98},
            jsPDF: {unit: 'in', format: 'letter', orientation: 'portrait'},
            html2canvas:  {
                scale: 2,
                height: element.scrollHeight
            },
        };
        // Generate pdf
        html2pdf().from(element).set(options).save();
    });
});