function getIndex(list, id) {
    for (var i = 0; i < list.length; i++ ) {
        if (list[i].id === id) {
            return i;
        }
    }

    return -1;
}


var taskApi = Vue.resource('/tasks{/id}');

Vue.component('task-form', {
    props: ['tasks', 'taskAttr'],
    data: function() {
        return {
            id: '',
            createDate: '',
            title: '',
            description: '',
            finishDate: '',
        }
    },
    watch: {
        taskAttr: function(newVal, oldVal) {
            this.id = newVal.id;
            this.createDate = newVal.createDate;
            this.title = newVal.title;
            this.description = newVal.description;
            this.finishDate = newVal.finishDate;
        }
    },
    template:
        '<div>' +
            '<input type="text" placeholder="Input title" v-model="title" />' +
            '<input type="text" placeholder="Input description" v-model="description" />' +
            '<input type="date" placeholder="Input description" v-model="finishDate" />' +
            '<input type="button" value="Записать" @click="save" />' +
            '</div>',
    methods: {
        save: function() {
            var task = {title: this.title, createDate: this.createDate,
                        description: this.description,
                        finishDate: this.finishDate};

            if (this.id) {
                taskApi.update({id: this.id}, task).then(result =>
                    result.json().then(data => {
                        var index = getIndex(this.tasks, data.id);
                        this.tasks.splice(index, 1, data);
                        this.id = ''
                        this.title = ''
                        this.description = ''
                        this.finishDate = ''
                    })
                )
            } else {
                taskApi.save({}, task).then(result =>
                    result.json().then(data => {
                        this.tasks.push(data);
                        this.title = ''
                        this.description = ''
                        this.finishDate = ''
                    })
                )
            }
        }
    }
});

Vue.component('task-row', {
    props: ['task', 'editMethod', 'tasks'],
    template: '<div>' +
        '<i>({{ task.id }})</i> <b>{{ task.title }}</b> {{ task.description }}' +
        '<span style="float: right; margin-right: 130px;">' +
        '{{ task.finishDate }}' +
        '</span>' +
        '<span style="position: absolute; right: 0">' +
            '<input type="button" value="Изменить" @click="edit" />' +
            '<input type="button" value="X" @click="del" />' +
        '</span>' +
        '</div>',
    methods: {
        edit: function() {
            this.editMethod(this.task);
        },
        del: function() {
            taskApi.remove({id: this.task.id}).then(result => {
                if (result.ok) {
                    this.tasks.splice(this.tasks.indexOf(this.task), 1)
                }
            })
        }
    }
});

Vue.component('tasks-list', {
  props: ['tasks'],
  data: function() {
    return {
        task: null
    }
  },
  template:
    '<div style="position: relative; width: 600px;">' +
        '<task-form :tasks="tasks" :taskAttr="task" />' +
        '<task-row v-for="task in tasks" :key="task.id" :task="task" ' +
            ':editMethod="editMethod" :tasks="tasks" />' +
    '</div>',
  created: function() {
    taskApi.get().then(result =>
        result.json().then(data =>
            data.forEach(task => this.tasks.push(task))
        )
    )
  },
  methods: {
    editMethod: function(task) {
        this.task = task;
    }
  }
});

var app = new Vue({
  el: '#app',
  template: '<tasks-list :tasks="tasks" />',
  data: {
    tasks: []
  }
});