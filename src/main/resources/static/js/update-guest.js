document.querySelector("#updateGuestForm").addEventListener("submit", async function (event) {
    event.preventDefault();

    const urlParts = window.location.pathname.split("/");
    const guestId = urlParts[urlParts.length - 1];

    const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute("content");
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute("content");

    const updatedData = {
        name: document.querySelector("#name").value.trim(),
        nationality: document.querySelector("#nationality").value.trim().toUpperCase(), // jeśli chcesz wielkimi
        dateOfBirth: document.querySelector("#birthDate").value
    };

    try {
        const response = await fetch(`/api/guests/${guestId}`, {
            method: "PATCH",
            headers: {
                "Content-Type": "application/json",
                [csrfHeader]: csrfToken
            },
            body: JSON.stringify(updatedData)
        });

        if (!response.ok) {
            const errorMessage = await response.text();
            throw new Error(errorMessage);
        }

        const updatedGuest = await response.json();
        alert(`Guest ${updatedGuest.name} updated successfully!`);

        document.querySelector("#name").value = updatedGuest.name;
        document.querySelector("#nationality").value = updatedGuest.nationality;
        document.querySelector("#birthDate").value = updatedGuest.dateOfBirth;
        const imageUrl = '/images/' + updatedGuest.name.toLowerCase().replace(/ /g, '-') + '.jpg';
        document.querySelector("#guestImage").setAttribute("src", imageUrl);

    } catch (error) {
        console.error("Error:", error);
        alert("Error updating guest: " + error.message);
    }
});
