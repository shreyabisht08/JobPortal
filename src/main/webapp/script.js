// Registration form validation
document.getElementById('registerForm').addEventListener('submit', function (e) {
    const username = document.getElementById('username').value.trim();
    const email = document.getElementById('email').value.trim();
    const password = document.getElementById('password').value.trim();

    if (username.length < 5) {
        alert('Username must be at least 5 characters long.');
        e.preventDefault();
    } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
        alert('Please enter a valid email address.');
        e.preventDefault();
    } else if (password.length < 8) {
        alert('Password must be at least 8 characters long.');
        e.preventDefault();
    }
});

// Login form validation
document.getElementById('loginForm').addEventListener('submit', function (e) {
    const username = document.getElementById('username').value.trim();
    const password = document.getElementById('password').value.trim();

    if (username === '' || password === '') {
        alert('Both fields are required.');
        e.preventDefault();
    }
});
