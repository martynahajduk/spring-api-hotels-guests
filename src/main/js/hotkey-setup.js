import hotkeys from 'hotkeys-js'

export function setupFormHotkey(formSelector) {
    hotkeys('ctrl+enter', (event) => {
        event.preventDefault()
        const form = document.querySelector(formSelector)
        if (form) {
            form.requestSubmit()
        }
    })
}
