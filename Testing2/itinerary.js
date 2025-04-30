// itinerary.js
const itineraries = {
  "alnwick": {
    "name": "Alnwick Castle",
    "image": "images/AlnwickCastle.jpg",
    "busPrice": 2.5,
    "ticketPrice": 1,
    "baseDeparture": "15:30",
    "return": "17:30",

    "outbound": [
      "Board the X15 bus from Haymarket Bus Station in Newcastle",
      "Service runs hourly on weekdays and every 2 hours on Sundays",
      "Journey time: Approx. 80–94 mins",
      "Disembark: Playhouse, Alnwick bus station"
    ],
    "walking": [
      "5-minute walk from the bus station to Alnwick Castle",
      "Walk 620 meters (approx. 8 mins) to the castle entrance"
    ],
    "returnJourney": [
      "Board from Bailiffgate or Alnwick Bus Station",
      "X15, X18 or X20 return to Newcastle",
      "Last bus from Alnwick departs at 22:28"
    ],
    "tips": [
      "Bus fare starts from £14 one-way",
      "Total distance ~53.59 km",
      "Nearby stops: Bailiffgate, Bowling Green, Bus Station"
    ]
  },
  "barnard": {
    "name": "Barnard Castle",
    "image": "images/BarnardCastle.jpg",
    "busPrice": 3,
    "ticketPrice": 1,
    "baseDeparture": "14:00",
    "return": "17:00",
    "outbound": [
      "Take X75 bus from Durham station",
      "Runs every 2 hours",
      "Journey: ~70 minutes",
      "Stop at Galgate"
    ],
    "walking": [
      "Walk 450 meters (~6 mins) from Galgate to the castle"
    ],
    "returnJourney": [
      "Use X76 or X75 back to Durham",
      "Last return at 20:15"
    ],
    "tips": [
      "Total cost: ~£14.50 round trip",
      "Good for half-day visit"
    ]
  },
  "bamburgh": {
    "name": "Bamburgh Castle",
    "image": "images/BamburghCastle.jpg",
    "busPrice": 3.2,
    "ticketPrice": 1.5,
    "baseDeparture": "13:30",
    "return": "16:00",
    "outbound": [
      "Board the X18 bus from Alnwick Bus Station",
      "Service runs every 90 minutes",
      "Disembark at Bamburgh Castle Stop"
    ],
    "walking": [
      "Walk 350 meters (~4 mins) from bus stop to castle entrance"
    ],
    "returnJourney": [
      "Return buses available hourly until 21:00"
    ],
    "tips": [
      "Fantastic sea views from the castle",
      "Popular with photographers"
    ]
  },
  "auckland": {
    "name": "Auckland Castle",
    "image": "images/AucklandCastle.jpg",
    "busPrice": 2.8,
    "ticketPrice": 2,
    "baseDeparture": "14:30",
    "return": "17:30",
    "outbound": [
      "Take bus 101 from Bishop Auckland Station",
      "Runs every 30 mins",
      "Walk to Castle from Market Place"
    ],
    "walking": [
      "Walk 400 meters (~5 mins) through town square to castle entrance"
    ],
    "returnJourney": [
      "Return buses to station available until 20:00"
    ],
    "tips": [
      "Known for beautiful gardens and art exhibitions"
    ]
  }
};

function getQueryParams() {
  const params = new URLSearchParams(window.location.search);
  return {
    castle: params.get("castle"),
    time: params.get("time"),
    visitors: parseInt(params.get("visitors")) || 1
  };
}

function renderItinerary(data, departureTime, visitorCount) {
  document.getElementById("castle-name").textContent = data.name;
  document.getElementById("castle-image").src = data.image;
  document.getElementById("castle-image").alt = data.name;

  document.getElementById("departure-time").textContent = departureTime || data.baseDeparture;
  document.getElementById("return-time").textContent = data.return;
  document.getElementById("visitor-count").value = visitorCount;

  populateList("outbound-journey", data.outbound);
  populateList("walking-directions", data.walking);
  populateList("return-journey", data.returnJourney);
  populateList("tips", data.tips);

  // 计算并显示总价 
  const totalCost = calculateTotalPrice(data, visitorCount);
  document.getElementById("castle-price").textContent = `£${totalCost.toFixed(2)}`;
  // 更新确认链接  
  const confirmLink = document.getElementById('confirm-link');
  confirmLink.href = `confirmation.html?castle=${data.name}&time=${departureTime}&visitors=${visitorCount}&total=${totalCost.toFixed(2)}`;
  // 设置背景图片为当前城堡图片
  document.body.style.backgroundImage = `url(${data.image})`;
}

function populateList(id, items) {
  const ul = document.getElementById(id);
  ul.innerHTML = "";
  items.forEach(item => {
    const li = document.createElement("li");
    li.textContent = item;
    ul.appendChild(li);
  });
}

function calculateTotalPrice(data, visitorCount) {
  const total = (data.busPrice + data.ticketPrice) * visitorCount;
  return total;
}

window.onload = function () {
  const { castle, time, visitors } = getQueryParams();
  const data = itineraries[castle];

  if (castle && data) {
    renderItinerary(data, time, visitors);
    document.getElementById("visitor-count").addEventListener("change", function () {
  const newVisitorCount = parseInt(this.value);
  const newTotal = calculateTotalPrice(data, newVisitorCount);

  // 更新价格显示
  document.getElementById("castle-price").textContent = `£${newTotal.toFixed(2)}`;

  // 更新确认按钮链接
  const confirmLink = document.getElementById('confirm-link');
  confirmLink.href = `confirmation.html?castle=${data.name}&time=${departureTime}&visitors=${newVisitorCount}&total=${newTotal.toFixed(2)}`;
});

  } else {
    document.body.innerHTML = "<h2>Castle itinerary not found.</h2><a href='index.html'>Back to Home</a>";
  }
};
