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

function calculateEMI(event) {
    event.preventDefault();

    let loanAmount = parseFloat(document.getElementById("loanAmount").value);
    let interestRate = parseFloat(document.getElementById("interestRate").value);
    let loanTenure = parseFloat(document.getElementById("loanTenure").value);

    if (isNaN(loanAmount) || loanAmount <= 0 || isNaN(interestRate) || interestRate <= 0 || isNaN(loanTenure) || loanTenure <= 0) {
        alert("Please enter valid positive numbers.");
        return false;
    }

    let monthlyRate = interestRate / (12 * 100);

    let numberOfMonths = loanTenure * 12;

    let emi = (loanAmount * monthlyRate * Math.pow(1 + monthlyRate, numberOfMonths)) / 
              (Math.pow(1 + monthlyRate, numberOfMonths) - 1);

    emi = emi.toFixed(2);

    let emiResult = document.getElementById("emiResult");
    emiResult.innerHTML = `Your EMI is <strong>₹${emi}</strong> per month.`;
    emiResult.classList.remove("d-none");

    return false;
}