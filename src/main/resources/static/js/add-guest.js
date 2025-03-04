document.querySelector('#addGuestForm').addEventListener('submit', async e => {
    e.preventDefault();

    const name = document.querySelector('#name').value;
    const dateOfBirth = document.querySelector('#birthDate').value;
    const nationality = document.querySelector('#nationality').value;
    const hotelName = document.querySelector('#hotelName').value;
    const roomNumbers = Array.from(document.querySelector('#roomNumbers').selectedOptions)
        .map(option => parseInt(option.value));

    const guestData = JSON.stringify({
        name,
        dateOfBirth,
        nationality,
        hotelName,
        roomNumbers
    });

    try {
        const response = await fetch('/api/guests', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: guestData
        });

        if (response.status === 201) {
            const guest = await response.json();
            alert(`Guest added successfully! Guest ID: ${guest.id}`);
            window.location = `/guests`;
        } else {
            const errorText = await response.text();
            alert(`Something went wrong: ${errorText}`);
        }
    } catch (error) {
        console.error('Fetch Error:', error);
        alert('An error occurred while adding the guest.');
    }
});
