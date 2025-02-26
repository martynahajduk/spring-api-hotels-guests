const removeGuestButtons = document.querySelectorAll('.remove-guest-button');
removeGuestButtons.forEach(button => button.addEventListener('click', async e => {
    const issueId = button.getAttribute('data-guest-id');
    button.disabled = true;
    const result = await fetch(`/api/guests/${guestId}`, {method: 'DELETE'});
    button.disabled = false;
    if (result.status === 204) {
        document.querySelector(`#guest-${guestId}`).remove();
        const guestTitle = document.querySelector('h1');
        const guestAmount = parseInt(guestTitle.getAttribute('data-guest-amount'), 10);
        guestTitle.innerHTML = `Issues (${guestAmount - 1})`;
        guestTitle.setAttribute('data-issue-amount', (guestAmount - 1).toString());
        } else {
            alert('Deleting failed.');
        }

}))

