document.addEventListener('DOMContentLoaded', function() {
    const guestId = window.location.pathname.split('/').pop()

    fetch(`/api/guests/${guestId}`)
        .then(response => response.json())
        .then(guest => {
            const guestDetails = document.getElementById('guestDetails')

            guestDetails.innerHTML = `
                <p><strong>Name:</strong> ${guest.name}</p>
                <p><strong>Date of Birth:</strong> ${guest.dateOfBirth}</p>
                <p><strong>Nationality:</strong> ${guest.nationality}</p>
                <p><strong>Hotel Name:</strong> ${guest.hotelName}</p>
                <p><strong>Room Number:</strong> ${guest.roomNumber}</p>
            `
        })
        .catch(error => console.error('Error fetching guest details:', error))
})
