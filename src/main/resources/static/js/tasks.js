function createTask(description="blank", status="created") {
    fetch('/api/tasks/create', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ 
            description: description,
            status: status,
        }),
    })
    .then(response => response.json())
    .then(data => {
        console.log('Task created:', data);
        // You can handle the response or redirect as needed
    })
    .catch(error => {
        console.error('Error creating task:', error);
    });
}

function deleteTask(taskId) {
    fetch(`/api/tasks/delete/${taskId}`, {
        method: 'DELETE',
    })
    .then(response => {
        if (response.status === 204) {
            console.log('Task deleted successfully');
        } else {
            console.error('Error deleting task');
            console.error(response);
        }
    })
    .catch(error => {
        console.error('Error deleting task:', error);
    });
}

function updateTask(taskId, description, status) {
    updatedTask = {
        description: description,
        status: status,
    }
    fetch(`/api/tasks/update/${taskId}`, {
        method: "PUT",
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(updatedTask),
    })
    .then(response => {
        if (response.status === 204) {
            console.log('Task deleted successfully');
        } else {
            console.error('Error deleting task');
            console.error(response);
        }
    })
    .catch(error => {
        console.error('Error deleting task:', error);
    });
}