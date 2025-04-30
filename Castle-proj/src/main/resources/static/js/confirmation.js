document.addEventListener("DOMContentLoaded", function () {
  const castle = localStorage.getItem("confirmCastle");
  const time = localStorage.getItem("confirmDeparture");
  const returnTime = localStorage.getItem("confirmReturn");
  const visitors = localStorage.getItem("confirmVisitors");
  const total = localStorage.getItem("confirmTotal");

  if (castle && time && returnTime && visitors && total) {
    document.getElementById('selected-castle').textContent = castle;
    document.getElementById('departure-time').textContent = time;
    document.getElementById('return-time').textContent = returnTime;
    document.getElementById('number-of-people').textContent = visitors;
    document.getElementById('total-cost').textContent = `£${parseFloat(total).toFixed(2)}`;
  } else {
    alert("Missing confirmation data. Please return and submit your itinerary again.");
  }
    
     document.querySelector('.payment-form').addEventListener('submit', function (event) {
    event.preventDefault();

    const cardNumber = document.getElementById('card-number').value;
    const expiryDate = document.getElementById('expiry-date').value;
    const cvv = document.getElementById('cvv').value;

    if (cardNumber && expiryDate && cvv) {
      alert('Payment Successful!');
    } else {
      alert('Please fill in all fields.');
    }
  });
});

    
//    
//});
//
//    
//    
////  const urlParams = new URLSearchParams(window.location.search);
////  const castle = urlParams.get('castle');
////  const time = urlParams.get('time');
////  const visitors = parseInt(urlParams.get('visitors'), 10);
////  const total = parseFloat(urlParams.get('total'));
////
////  document.getElementById('selected-castle').textContent = castle;
////  document.getElementById('departure-time').textContent = time;
////  document.getElementById('number-of-people').textContent = visitors;
////  document.getElementById('return-time').textContent = "17:30";
////  document.getElementById('total-cost').textContent = `£${total.toFixed(2)}`;
//});
////
//console.log('Castle:', castle);
//console.log('Departure Time:', time);
//console.log('Visitors:', visitors);
//console.log('Total Cost:', total);
//if (castle && time && !isNaN(visitors) && !isNaN(total)) {
//  document.getElementById('selected-castle').textContent = castle;
//  document.getElementById('departure-time').textContent = time;
//  document.getElementById('number-of-people').textContent = visitors;
//  document.getElementById('return-time').textContent = "17:30";
//  document.getElementById('total-cost').textContent = `£${total.toFixed(2)}`;
//} else { alert('Some data is missing or incorrect. Please go back and make your selections again.'); };
////
////document.getElementById('return-time').textContent = "17:30";
//////
////const totalCost = calculateTotalCost(visitors);
////document.getElementById('total-cost').textContent = `£${totalCost.toFixed(2)}`;
//
//
//
//document.querySelector('.payment-form').addEventListener('submit', function (event) {
//  event.preventDefault();
//
//  const cardNumber = document.getElementById('card-number').value;
//  const expiryDate = document.getElementById('expiry-date').value;
//  const cvv = document.getElementById('cvv').value;
//
//  if (cardNumber && expiryDate && cvv) {
//    alert('Payment Successful!');
//  } else {
//    alert('Please fill in all fields.');
//  }
//});
//
//function calculateTotalCost(visitorNumber) {
//  const costPerPerson = 7.5;
//  return visitorNumber * costPerPerson;
//}
//
//document.querySelector('.payment-form').addEventListener('submit', function (event) {
//  event.preventDefault();
//
//  const cardNumber = document.getElementById('card-number').value;
//  const expiryDate = document.getElementById('expiry-date').value;
//  const cvv = document.getElementById('cvv').value;
//
//  if (cardNumber && expiryDate && cvv) {
//    alert('Payment Successful!');
//  } else {
//    alert('Please fill in all fields.');
//  }
//});
//
//
//function calculateReturnTime(departureTime) {
//  const [hour, minute] = departureTime.split(':');
//  let returnHour = parseInt(hour) + 7;
//  if (returnHour >= 24) returnHour -= 24;
//  return `${returnHour.toString().padStart(2, '0')}:${minute}`;
//}




