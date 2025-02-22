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

document.addEventListener("DOMContentLoaded", function() {
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
});

function toggleSubmenu(event, submenuId) {
	event.preventDefault();
	let submenu = document.getElementById(submenuId);
	let icon = event.target.querySelector("i");

	if (submenu.classList.contains("show")) {
		submenu.classList.remove("show");
		icon.classList.replace("fa-minus", "fa-plus");
	} else {
		submenu.classList.add("show");
		icon.classList.replace("fa-plus", "fa-minus");
	}
}

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

