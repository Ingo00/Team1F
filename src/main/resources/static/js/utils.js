/**
 * Displays an error message in a designated error container.
 *
 * @param {string} message - The error message to display
 */
export function showError(message) {
  const container = document.getElementById("error-message");
  if (container) {
    container.innerText = message;
    container.style.display = "block";
  }
}
