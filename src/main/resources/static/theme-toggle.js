document.addEventListener("DOMContentLoaded", function () {
    const toggleThemeButton = document.getElementById("toggle-theme");
    const body = document.body;

    // Carregar o tema salvo no localStorage
    if (localStorage.getItem("theme") === "light") {
        body.classList.add("light-mode");
    }

    toggleThemeButton.addEventListener("click", function () {
        // Alterna a classe light-mode no body
        body.classList.toggle("light-mode");

        // Salvar o tema no localStorage
        if (body.classList.contains("light-mode")) {
            localStorage.setItem("theme", "light");
        } else {
            localStorage.setItem("theme", "dark");
        }
    });
});
