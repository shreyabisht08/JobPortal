document.getElementById('registerForm').addEventListener('submit', function (e) {
    const username = document.getElementById('username').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    if (username.length < 5) {
        alert('Username must be at least 5 characters long.');
        e.preventDefault();
    } else if (!email.includes('@')) {
        alert('Please enter a valid email address.');
        e.preventDefault();
    } else if (password.length < 8) {
        alert('Password must be at least 8 characters long.');
        e.preventDefault();
    }
});

document.getElementById('loginForm').addEventListener('submit', function (e) {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    if (!username || !password) {
        alert('All fields are required.');
        e.preventDefault();
    }
});
