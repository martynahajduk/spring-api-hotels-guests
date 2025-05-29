document.addEventListener('DOMContentLoaded', function() {
    // Fetch all guests on page load
    fetch('/api/guests')
        .then(response => response.json())
        .then(guests => {
            const guestList = document.getElementById('guestList')

            guests.forEach(guest => {
                const row = document.createElement('tr')

                row.innerHTML = `
                    <td>${guest.guestId}</td>
                    <td>${guest.name}</td>
                    <td>${guest.dateOfBirth}</td>
                    <td>${guest.nationality}</td>
                    <td>${guest.hotelName}</td>
                    <td>${guest.roomNumber}</td>
                    <td>
                        <button class="btn btn-danger remove-guest-button" data-guest-id="${guest.guestId}">Delete</button>
                    </td>
                `
                guestList.appendChild(row)
            })

            document.querySelectorAll('.remove-guest-button').forEach(button => {
                button.addEventListener('click', async function() {
                    const guestId = button.getAttribute('data-guest-id')

                    const response = await fetch(`/api/guests/${guestId}`, {
                        method: 'DELETE',
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    })

                    if (response.ok) {
                        // Remove the guest row from the table
                        const guestRow = button.closest('tr')
                        guestRow.remove()
                    } else {
                        alert('Error: Unable to delete guest')
                    }
                })
            })
        })
        .catch(error => console.error('Error fetching guests:', error))
})
