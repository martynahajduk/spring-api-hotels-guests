const loadAssigneesButton = document.querySelector('#load-assignees');
const issueId = loadAssigneesButton.getAttribute('data-issue-id');
const assignedDevelopers = document.querySelector('#assigned-developers');
loadAssigneesButton.addEventListener('click', async e => {
    const response = await fetch(`/api/issues/${issueId}/assignees`);
    const assignees = await response.json();
    const assigneesList = assignees.map(assignee => `<li><a href="/developers/${assignee.id}">${assignee.name}</a></li>`).join('');
    assignedDevelopers.innerHTML = '<ul class="mb-0">' + assigneesList + '</ul>';
});