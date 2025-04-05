
document.addEventListener("DOMContentLoaded", () => {
  const flightId = localStorage.getItem("selectedFlightId");
  const form = document.getElementById("booking-form");

  if (form) {
    form.addEventListener("submit", async (e) => {
      e.preventDefault();
      const name = document.getElementById("name").value;
      const email = document.getElementById("email").value;

      try {
        const res = await fetch("/bookings", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({ flightId, name, email })
        });

        if (!res.ok) throw new Error("Booking failed");

        const confirmation = await res.json();
        localStorage.setItem("bookingConfirmation", JSON.stringify(confirmation));
        window.location.href = "/confirmation";
      } catch (err) {
        alert("Booking failed. Try again.");
        console.error(err);
      }
    });
  }
});
