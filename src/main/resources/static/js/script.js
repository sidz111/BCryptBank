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

/*This is not working, i will work on it.*/
/*document.addEventListener("DOMContentLoaded", function() {
	document.getElementById("readMoreBtn").addEventListener("click", function() {
		var moreText = document.getElementById("moreText");
		var btnText = document.getElementById("readMoreBtn");

		if (moreText.classList.contains("d-none")) {
			moreText.classList.remove("d-none");
			btnText.innerHTML = "Read Less";
		} else {
			moreText.classList.add("d-none");
			btnText.innerHTML = "Read More";
		}
	});
});*/

