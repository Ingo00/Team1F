
document.addEventListener("DOMContentLoaded", () => {
  const resultsContainer = document.getElementById("results");
  const flights = JSON.parse(localStorage.getItem("searchResults")) || [];

  if (flights.length === 0) {
    resultsContainer.innerHTML = "<p>No flights found.</p>";
    return;
  }

  flights.forEach(flight => {
    const div = document.createElement("div");
    div.classList.add("flight");
    div.innerHTML = `
      <p><strong>${flight.origin}</strong> → <strong>${flight.destination}</strong></p>
      <p>Departure: ${flight.departureTime}</p>
      <p>Price: ${flight.price} ISK</p>
      <button onclick="bookFlight(${flight.id})">Book</button>
    `;
    resultsContainer.appendChild(div);
  });
});

function bookFlight(flightId) {
  localStorage.setItem("selectedFlightId", flightId);
  window.location.href = "/book";
}
