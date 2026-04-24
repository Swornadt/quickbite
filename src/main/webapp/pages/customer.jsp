<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Customer Management</title>

<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet">

<style>
body {
  font-family: 'Poppins', sans-serif;
  background: #f5f6fa;
  margin: 0;
}

/* HEADER */
.top {
  width: 956px;
  height: 78px;
  margin: auto;
  padding: 20px 60px;
  border-bottom: 2px solid #e5e7eb;
  display: flex;
  justify-content: flex-end;
  align-items: center;
  gap: 15px;
  background: white;
}

/* ICONS */
.icons {
  display: flex;
  gap: 12px;
  margin-right: 15px;
}
.icon {
  width: 22px;
  height: 22px;
  cursor: pointer;
}

/* USER */
.user {
  text-align: right;
  margin-right: 10px;
}
.user-name { font-weight: 600; }
.user-role { font-size: 12px; color: gray; }

.avatar {
  width: 40px;
  height: 40px;
  border-radius: 50%;
}

/* CONTAINER */
.container {
  width: 956px;
  margin: auto;
}

/* TITLE */
h1 {
  width: 815px;
  font-size: 26px;
  font-weight: 600;
  margin: 20px 0;
}

/* SEARCH */
.search {
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}
.search {
  display: flex;
  justify-content: flex-end;
  width: 815px;
  margin-bottom: 20px;
}

.search input {
  width: 400px;
  height: 42px;
  border-radius: 10px;
  padding: 5px 24px;
  border: none;
  background: #f1f1f1;
}

/* TABS */
.tabs {
  width: 815px;
  height: 70px;
  border: 1px solid #e5e7eb;
  border-radius: 10px;
  padding: 12px 21px;
  display: flex;
  align-items: center;
  gap: 20px;
  background: white;
  margin-bottom: 40px;
}

.tab {
  padding: 10px 18px;
  border: none;
  background: transparent;
  cursor: pointer;
  font-weight: 500;
}

.active {
  background: #fFDAB9;
  border-radius: 10px;
}

/* GRID */
.grid {
  width: 815px;
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  row-gap: 81px;
  column-gap: 81px;
}

/* CARD */
.card {
  width: 367px;
  height: 212px;
  background: #eee6e3;
  padding: 24px;
  border-radius: 20px;
}

/* CARD TOP */
.card-top {
  display: flex;
  align-items: center;
  gap: 15px;
}

.photo {
  width: 60px;
  height: 60px;
  border-radius: 50%;
  object-fit: cover;
}

.name {
  font-weight: 600;
}
.phone {
  color: gray;
  font-size: 14px;
}

/* BUTTONS */
.buttons {
  display: flex;
  gap: 10px;
  margin-top: 15px;
}

.btn {
  flex: 1;
  padding: 10px;
  border: none;
  border-radius: 25px;
  color: white;
  cursor: pointer;
  font-weight: 500;
}

.approve { background: #14AE5C; }
.reject { background: #DB4A1E; }

</style>
</head>

<body>

<!-- HEADER -->
<div class="top">

  <!-- ICONS -->
  <div class="icons">
    <!-- Notification -->
    <svg class="icon" viewBox="0 0 24 24" fill="none" stroke="black" stroke-width="2">
      <path d="M15 17h5l-1-1V11a6 6 0 10-12 0v5l-1 1h5"></path>
      <circle cx="12" cy="19" r="2"></circle>
    </svg>

    <!-- Chat -->
    <svg class="icon" viewBox="0 0 24 24" fill="none" stroke="black" stroke-width="2">
      <path d="M21 12c0 4-4 7-9 7-1 0-2-.2-3-.5L3 20l1-3c-.6-1-.9-2-.9-3 0-4 4-7 9-7s9 3 9 7z"></path>
    </svg>
  </div>

  <div class="user">
    <div class="user-name">Paseo Pascal</div>
    <div class="user-role">Admin</div>
  </div>

  <img class="avatar" src="admin.jpg">
</div>

<!-- MAIN -->
<div class="container">
  <h1>Customer Management</h1>

  <!-- SEARCH -->
  <div class="search">
    <input type="text" placeholder="🔍...">
  </div>

  <!-- TABS -->
  <div class="tabs">
  <button class="tab active" onclick="showTab('active', this)">Active Customers</button>
  <button class="tab" onclick="showTab('pending', this)">Pending Customers</button>
</div>

  <!-- PENDING -->
  <div class="grid" id="pending" style="display:none;">

    <!-- CARD -->
    <div class="card">
      <div class="card-top">
        <img class="photo" src="customer.jpg">
        <div>
          <div class="name">Alison Burgers</div>
          <div class="phone">9712345678</div>
        </div>
      </div>
      <div class="buttons">
        <button class="btn approve" onclick="approve(this)">Approve</button>
        <button class="btn reject" onclick="removeCard(this)">Reject</button>
      </div>
    </div>

    <!-- COPY -->
    <div class="card">
      <div class="card-top">
        <img class="photo" src="customer.jpg">
        <div>
          <div class="name">Sabrina Burgers</div>
          <div class="phone">9712345678</div>
        </div>
      </div>
      <div class="buttons">
        <button class="btn approve" onclick="approve(this)">Approve</button>
        <button class="btn reject" onclick="removeCard(this)">Reject</button>
      </div>
    </div>

    <div class="card">
      <div class="card-top">
        <img class="photo" src="customer.jpg">
        <div>
          <div class="name">Sahil Burgers</div>
          <div class="phone">9712345678</div>
        </div>
      </div>
      <div class="buttons">
        <button class="btn approve" onclick="approve(this)">Approve</button>
        <button class="btn reject" onclick="removeCard(this)">Reject</button>
      </div>
    </div>

    <div class="card">
      <div class="card-top">
        <img class="photo" src="customer.jpg">
        <div>
          <div class="name">Alison Burgers</div>
          <div class="phone">9712345678</div>
        </div>
      </div>
      <div class="buttons">
        <button class="btn approve" onclick="approve(this)">Approve</button>
        <button class="btn reject" onclick="removeCard(this)">Reject</button>
      </div>
    </div>

  </div>

  <!-- ACTIVE -->
  <div class="grid" id="active"></div>

</div>

<!-- SCRIPT -->
<script>
function showTab(type, el) {
  document.getElementById('active').style.display = type === 'active' ? 'grid' : 'none';
  document.getElementById('pending').style.display = type === 'pending' ? 'grid' : 'none';

  document.querySelectorAll('.tab').forEach(btn => btn.classList.remove('active'));
  el.classList.add('active');
}

function approve(btn) {
  event.stopPropagation(); // FIX
  const card = btn.closest('.card');
  document.getElementById('active').appendChild(card);
}

function removeCard(btn) {
  event.stopPropagation(); // FIX
  const card = btn.closest('.card');
  card.remove();
}
</script>

</body>
</html>