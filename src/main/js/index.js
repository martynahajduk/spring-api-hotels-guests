import '../scss/main.scss'
import 'bootstrap'

// document.getElementById('navSearch').addEventListener('click', () => {
//     document.getElementById('searchSection').style.display = 'block'
//     document.getElementById('addSection').style.display = 'none'
// })
//
// document.getElementById('navAdd').addEventListener('click', () => {
//     document.getElementById('searchSection').style.display = 'none'
//     document.getElementById('addSection').style.display = 'block'
// })
//
// document.getElementById('searchForm').addEventListener('submit', async (e) => {
//     e.preventDefault()
//     const guestName = document.getElementById('guestNameInput').value
//     const response = await fetch(`http://localhost:8080/api/guest-rooms/search?guestName=${guestName}`)
//     const data = await response.json()
//
//     const resultsArea = document.getElementById('resultsArea')
//     if (data.length === 0) {
//         resultsArea.innerHTML = '<p>No results found.</p>'
//     } else {
//         resultsArea.innerHTML = `
//       <table class="table table-striped mt-3">
//         <thead><tr><th>Room</th><th>Guest</th><th>Hotel</th></tr></thead>
//         <tbody>
//           ${
//             data.map(row => `<tr><td>${row.roomNumber}</td><td>${row.guestName}</td><td>${row.hotelId}</td></tr>`).join(
//                 ''
//             )
//         }
//         </tbody>
//       </table>
//     `
//     }
// })
//
// document.getElementById('addForm').addEventListener('submit', async (e) => {
//     e.preventDefault()
//     const form = e.target
//
//     const response = await fetch('http://localhost:8080/api/guest-rooms/add', {
//         method: 'POST',
//         headers: { 'Content-Type': 'application/json' },
//         body: JSON.stringify({
//             guestId: form.guestId.value,
//             roomNumber: form.roomNumber.value,
//             hotelId: form.hotelId.value
//         })
//     })
//
//     if (response.ok) {
//         document.getElementById('addSuccess').style.display = 'block'
//         form.reset()
//     }
// })
