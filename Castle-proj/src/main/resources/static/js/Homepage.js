document.addEventListener("DOMContentLoaded", function () {
    // Submit button -> schedules
    const submitTripBtn = document.getElementById("submitTrip");
    if (submitTripBtn) {
        submitTripBtn.addEventListener("click", () => {
            const selectedCastle = document.getElementById("castle-select").value;
            const departureTime = document.getElementById("departure-time").value;
            const returnTime = document.getElementById("return-time").value;
            const visitorNumber = document.getElementById("visitor-number").value;
            const travelDay = document.getElementById("travel-day").value;

            if (!selectedCastle || !departureTime || !returnTime || !visitorNumber || !travelDay) {
                alert("Please select castle, departure time, return time, and number of visitors.");
                return;
            }

            const params = new URLSearchParams({
                departTime: departureTime,
                returnTime: returnTime,
                castleName: selectedCastle,
                noOfVisitors: visitorNumber,
                travelDay: travelDay
            });

            window.location.href = `/schedules?${params.toString()}`;
        });
    }
    // Castle information button -> castle
    document.querySelectorAll('.info-btn').forEach(button => {
        button.addEventListener('click', function () {
            const selectedCastleName = this.getAttribute('data-castle');
            if (selectedCastleName) {
                window.location.href = `/castle?name=${encodeURIComponent(selectedCastleName)}`;
            } else {
                alert("Castle URL not found.");
            }
        });
    });
});

