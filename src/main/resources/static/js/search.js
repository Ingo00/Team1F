
document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("search-form");
  if (form) {
    form.addEventListener("submit", async (e) => {
      e.preventDefault();
      const origin = document.getElementById("origin").value;
      const destination = document.getElementById("destination").value;
      const date = document.getElementById("departureDate").value;

      try {
        const res = await fetch(`/flights/search?origin=${origin}&destination=${destination}&departureDate=${date}`);
        const flights = await res.json();
        localStorage.setItem("searchResults", JSON.stringify(flights));
        window.location.href = "/results";
      } catch (error) {
        alert("Flight search failed. Try again.");
        console.error(error);
      }
    });
  }
});
