import dayjs from 'dayjs'
import { validateGuestForm } from './guest-validation.js'
import { setupFormHotkey } from './hotkey-setup.js'

setupFormHotkey('#addGuestForm')

document.querySelector('#addGuestForm').addEventListener('submit', async e => {
    e.preventDefault()

    if (!validateGuestForm()) {
        return
    }

    const name = document.querySelector('#name').value
    const dateOfBirth = document.querySelector('#birthDate').value
    const nationality = document.querySelector('#nationality').value
    const hotelName = document.querySelector('#hotelName').value
    const roomNumbers = Array.from(document.querySelector('#roomNumbers').selectedOptions)
        .map(option => parseInt(option.value))

    const birthDate = dayjs(dateOfBirth)
    const today = dayjs()
    const age = today.diff(birthDate, 'year')

    // its blocked if under 18, i didnt have any other idea
    if (age < 18) {
        const errorBox = document.querySelector('#errorBox')
        if (errorBox) {
            errorBox.textContent = `Guest must be at least 18 years old. Age: ${age}`
            errorBox.classList.remove('d-none')
        } else {
            alert(`Guest must be at least 18 years old. Age: ${age}`)
        }
        return
    }

    const guestData = JSON.stringify({
        name,
        dateOfBirth,
        nationality,
        hotelName,
        roomNumbers
    })

    try {
        const response = await fetch('/api/guests', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: guestData
        })

        if (response.status === 201) {
            const guest = await response.json()
            alert(`Guest added successfully! Guest ID: ${guest.id}`)
            window.location = `/guests`
        } else {
            const errorText = await response.text()
            alert(`Something went wrong: ${errorText}`)
        }
    } catch (error) {
        console.error('Fetch Error:', error)
        alert('An error occurred while adding the guest.')
    }
})
