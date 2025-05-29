import dayjs from 'dayjs'

export function calculateAge(birthDateString) {
    const birthDate = dayjs(birthDateString)
    const today = dayjs()
    return today.diff(birthDate, 'year')
}
