document.addEventListener("DOMContentLoaded", function () {
  const searchInput = document.getElementById("search-input");
  const searchBtn = document.getElementById("searchBtn");
  const searchSubmitBtn = document.getElementById("searchSubmitBtn");
  const searchBox = document.getElementById("search-box");
  const castleIds = ["alnwick", "auckland", "bamburgh", "barnard"];

  // 搜索框展开
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

  // ✅ 用户登录状态控制菜单逻辑
  const loggedInUser = localStorage.getItem("loggedInUser");
  const userIconBtn = document.getElementById("userIconBtn");
  const dropdown = document.getElementById("dropdown");
  const logoutBtn = document.getElementById("logout-btn");
  const signInOption = document.getElementById("sign-in-option");
  const accountLink = document.getElementById("account-link");
  const authOnlyElements = document.querySelectorAll(".auth-only");

  // 控制显示的逻辑
  if (loggedInUser) {
    // 登录状态：隐藏 Sign In，显示其他
    if (signInOption) signInOption.classList.add("hidden");
    authOnlyElements.forEach(el => el.classList.remove("hidden"));

    if (logoutBtn) {
      logoutBtn.addEventListener("click", () => {
        localStorage.removeItem("loggedInUser");
        location.reload();
      });
    }
  } else {
    // 未登录状态：显示 Sign In，隐藏其他
    if (signInOption) signInOption.classList.remove("hidden");
    authOnlyElements.forEach(el => el.classList.add("hidden"));
  }

  // 图标点击：展开下拉菜单
  if (userIconBtn) {
    userIconBtn.addEventListener("click", (e) => {
      e.stopPropagation();
      dropdown.classList.toggle("hidden");
    });
  }

  // 点击空白区域关闭下拉菜单
  document.addEventListener("click", () => {
    if (!dropdown.classList.contains("hidden")) {
      dropdown.classList.add("hidden");
    }
  });
});

// itinerary
document.querySelectorAll(".plan-btn").forEach(button => {
  button.addEventListener("click", function () {
    const selectedCastle = this.getAttribute('data-castle');
    const departureTime = document.getElementById('departure-time').value;
    const visitorNumber = document.getElementById('visitor-number').value;
    if (!castle || !time || !visitors) {
      alert("Please select departure time and number of visitors.");
      return;
    }
    // 将选择存储在本地存储中，以便在其他页面使用  
    localStorage.setItem('selectedCastle', selectedCastle);
    localStorage.setItem('departureTime', departureTime);
    localStorage.setItem('visitorNumber', visitorNumber);

    // 使用URL参数导航到确认页面
    window.location.href = `confirmation.html?castle=${selectedCastle}&time=${departureTime}&visitors=${visitorNumber}`;
  });
});

