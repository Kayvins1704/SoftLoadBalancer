In this project, a Software based Load Balancer has 
been created wherein a weighted round robin approach 
has been implemented and a minimum of two servers
are to be routed to by the LB

In Main class (which is acting as the client), 
we need to specify the weight being assigned to each 
to each server in the input and then select the 
available strategy present in the load balancing 
strategies and then accordingly specify as to how we 
are going to be sending requests to the Load Balancer
(whether one by one or in bulk mode)