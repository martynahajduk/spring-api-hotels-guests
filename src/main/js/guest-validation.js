import validator from 'validator'

export function validateGuestForm() {
    const name = document.querySelector('#name').value.trim()
    const birthDate = document.querySelector('#birthDate').value
    const nationality = document.querySelector('#nationality').value.trim()
    const hotelName = document.querySelector('#hotelName').value
    const roomNumbers = Array.from(document.querySelector('#roomNumbers').selectedOptions)

    const errorBox = document.querySelector('#errorBox')
    const errors = []

    if (validator.isEmpty(name)) {
        errors.push('Name is required.')
    }

    if (!validator.isDate(birthDate)) {
        errors.push('A valid birth date is required.')
    }

    if (validator.isEmpty(nationality)) {
        errors.push('Nationality is required.')
    }

    if (validator.isEmpty(hotelName)) {
        errors.push('You must select a hotel.')
    }

    if (roomNumbers.length === 0) {
        errors.push('Select at least one room.')
    }

    if (errors.length > 0) {
        errorBox.classList.remove('d-none')
        errorBox.innerHTML = errors.map((e) => `<p>${e}</p>`).join('')
        return false
    } else {
        errorBox.classList.add('d-none')
        errorBox.innerHTML = ''
        return true
    }
}
