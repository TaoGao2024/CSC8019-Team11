document.addEventListener("DOMContentLoaded", function () {
  const params = new URLSearchParams(window.location.search);
  const castleId = params.get("castle");

  const castleData = {
    alnwick: {
      name: "Alnwick Castle",
       image: "images/AlnwickCastle.jpg",
      description: "Alnwick Castle is one of the largest inhabited castles in England and featured in the Harry Potter films.",
      website: "https://www.alnwickcastle.com/",
      highlights: [
        "Used in Harry Potter and Downton Abbey",
        "Home of the Duke of Northumberland",
        "Stunning medieval architecture"
      ]
    },
    auckland: {
      name: "Auckland Castle",
        image: "images/AucklandCastle.jpg",
      description: "Auckland Castle is a historic palace with beautiful gardens, part of The Auckland Project.",
      website: "https://aucklandproject.org/attraction/auckland-palace/",
      highlights: [
        "Beautiful restored palace",
        "Art and history exhibitions",
        "Family-friendly outdoor space"
      ]
    },
    bamburgh: {
      name: "Bamburgh Castle",
        image: "images/BamburghCastle.jpg",
      description: "Bamburgh Castle is a coastal fortress offering panoramic sea views and rich historical artifacts.",
      website: "https://www.bamburghcastle.com/",
      highlights: [
        "Overlooks Northumberland coast",
        "Viking and Norman heritage",
        "Breathtaking sea views"
      ]
    },
    barnard: {
      name: "Barnard Castle",
        image: "images/BarnardCastle.jpg",
      description: "Barnard Castle is a scenic ruin located on a cliff above the River Tees, perfect for history lovers.",
      website: "https://www.english-heritage.org.uk/visit/places/barnard-castle/",
      highlights: [
        "Scenic views over River Tees",
        "Historic medieval ruins",
        "Operated by English Heritage"
      ]
    }
  };

  const data = castleData[castleId];

  if (!data) {
    document.body.innerHTML = "<h2>Castle not found.</h2><a href='HomePage.html'>Return to Home</a>";
    return;
  }

  document.getElementById("castle-name").textContent = data.name;
  document.getElementById("castle-description").textContent = data.description;
  document.getElementById("castle-link").href = data.website;
  document.getElementById("castle-link").textContent = data.website;

  const list = document.getElementById("castle-highlights");
  list.innerHTML = "";
  data.highlights.forEach(item => {
    const li = document.createElement("li");
    li.textContent = item;
    list.appendChild(li);
  });

  document.body.style.backgroundImage = `url(${data.image})`;
});
