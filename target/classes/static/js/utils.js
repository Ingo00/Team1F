
export function showError(message) {
  const container = document.getElementById("error-message");
  if (container) {
    container.innerText = message;
    container.style.display = "block";
  }
}
