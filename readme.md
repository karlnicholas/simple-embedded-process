Demonstrate issue with EJBLocal example of JBPM embedded processes

simple-process sample.bpmn file has been edited (with eclipse plugin) to change the simple hello script to a user task. 

The sample.bpmn user task has an actor "New Actor" that it assigns the task to.

Running the application starts the process and finds the task but ActualOwner property and the CreatedBy are empty strings.

Compare this to the my-jbpm-basic spring boot application (found in my github account) which deploys the same sample.bpmn process, starts it and displays the task ActualOwner and CreatedBy properties which have the value of "New Actor".

