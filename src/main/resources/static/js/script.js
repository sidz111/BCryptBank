document.getElementById("togglePassword").addEventListener("click", function() {
	let passwordField = document.getElementById("password");
	let icon = this.querySelector("i");
	if (passwordField.type === "password") {
		passwordField.type = "text";
		icon.classList.replace("bi-eye-slash", "bi-eye");
	} else {
		passwordField.type = "password";
		icon.classList.replace("bi-eye", "bi-eye-slash");
	}
});
