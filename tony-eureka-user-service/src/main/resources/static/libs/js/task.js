function queryTask(assignee) {
    $.ajax.post("http://localhost:8080/v1/task/query", {
        "assignee": assignee
    }, function success(data) {
        if (data.success) {
            const taskList = data.data;
            if (taskList.size() > 0) {
                return taskList;
            }
        }
    }, function error(error) {
        console.log(error)
    })
}

function finishTask(taskId) {
    $.ajax.get("http://localhost:8080/v1/task/finish", {
        "taskId": taskId
    }, function success(data) {
        if (data.success) {
            alert("taskId 任务已处理");
        }
    }, function error(error) {
        console.log(error)
    })
}