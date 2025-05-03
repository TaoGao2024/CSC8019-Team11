document.addEventListener("DOMContentLoaded", function () {
  const searchInput = document.getElementById("search-input");
  const searchBtn = document.getElementById("searchBtn");
  const searchSubmitBtn = document.getElementById("searchSubmitBtn");
  const searchBox = document.getElementById("search-box");
  const castleIds = ["alnwick", "auckland", "bamburgh", "barnard"];

  // Search box expands
  if (searchBtn && searchBox) {
    searchBtn.addEventListener("click", function (event) {
      event.stopPropagation();
      searchBox.classList.toggle("active");
    });
  }

  document.addEventListener("click", function (e) {
    if (searchBox && !searchBox.contains(e.target) && e.target !== searchBtn) {
      searchBox.classList.remove("active");
    }
  });

  function searchCastle() {
    const query = searchInput.value.trim().toLowerCase();
    if (!query) {
      alert("Please enter a castle name.");
      return;
    }
    const matched = castleIds.find((id) => query.includes(id) || id.includes(query));
    if (matched) {
        alert(`Found castle: ${matched}`);
      document.getElementById(matched).scrollIntoView({ behavior: "smooth" });
    } else {
      alert("Castle not found. Please check the spelling.");
    }
  }

  if (searchSubmitBtn) {
    searchSubmitBtn.addEventListener("click", searchCastle);
  }
  if (searchInput) {
    searchInput.addEventListener("keypress", function (e) {
      if (e.key === "Enter") searchCastle();
    });
  }

  // User control login and register
  const loggedInUser = localStorage.getItem("loggedInUser");
  const userIconBtn = document.getElementById("userIconBtn");
  const dropdown = document.getElementById("dropdown");
  const logoutBtn = document.getElementById("logout-btn");
  const signInOption = document.getElementById("sign-in-option");
  const authOnlyElements = document.querySelectorAll(".auth-only");

  if (loggedInUser) {
    // State of login
    if (signInOption) signInOption.classList.add("hidden");
    authOnlyElements.forEach(el => el.classList.remove("hidden"));

    if (logoutBtn) {
      logoutBtn.addEventListener("click", () => {
        localStorage.removeItem("loggedInUser");
        location.reload();
      });
    }
  } else {
    // User does not login -> sign in button
    if (signInOption) signInOption.classList.remove("hidden");
    authOnlyElements.forEach(el => el.classList.add("hidden"));
  }

  // User account
  if (userIconBtn) {
    userIconBtn.addEventListener("click", (e) => {
      e.stopPropagation();
      dropdown.classList.toggle("hidden");
    });
  }

  // Hide account
  document.addEventListener("click", () => {
    if (!dropdown.classList.contains("hidden")) {
      dropdown.classList.add("hidden");
    }
});

// Submit button -> itinerary
  const submitTripBtn = document.getElementById("submitTrip");
  if (submitTripBtn) {
    submitTripBtn.addEventListener("click", () => {
      const selectedCastle = document.getElementById("castle-select").value;
      const departureTime = document.getElementById("departure-time").value;
      const returnTime = document.getElementById("return-time").value;
      const visitorNumber = document.getElementById("visitor-number").value;

      if (!selectedCastle || !departureTime || !returnTime || !visitorNumber) {
        alert("Please select castle, departure time, return time, and number of visitors.");
        return;
      }

      const params = new URLSearchParams({
        departTime: departureTime,
        returnTime: returnTime,
        castleName: selectedCastle,
        noOfVisitors: visitorNumber
      });
      console.log(departureTime);
      console.log(returnTime);
      console.log(selectedCastle)
      console.log(visitorNumber)

      window.location.href = `/schedules?${params.toString()}`;
    });
  }
document.querySelectorAll('.info-btn').forEach(button => {
  button.addEventListener('click', function () {
    const selectedCastleName = this.getAttribute('data-castle');
    // const castleNameMap = {
    //   alnwick: "Alnwick Castle",
    //   auckland: "Auckland Castle",
    //   bamburgh: "Bamburgh Castle",
    //   barnard: "Barnard Castle"
    // };
    // const selectedCastleName = castleNameMap[selectedCastleKey];
    if (selectedCastleName) {
      window.location.href = `/castle?name=${encodeURIComponent(selectedCastleName)}`;
    } else {
      alert("Castle mapping not found.");
    }
  });
});

});

