# Component-Oriented Programming in Java

Component oriented programming is a method of decomposing a problem into major sections ("components") each which has a particular role or domain of responsibility in solving the problem.

Businesses are somewhat component oriented as the consist of departments "accounting", "purchasing", "sales", which have full domain over certain tasks which the business needs to provide.

The differences between object-orientation and component orientation are ones of scale and re-usability. Object oriented thinking tends to focus on the tight integration of small objects, where objects are reused across an entire software offering. Component oriented thinking tends to focus on silos of responsibility which operate mostly independently and may or may not share common objects with other components.

JDBC database drivers are a good example of Component oriented thinking (implemented in an object oriented world). You don't care which database driver you code against, or the details of the database communications, as your component (The JDBC driver) handles all of that internally.

The different types of EJBs are also components. For each problem you wish to solve, you should be selecting the J2EE component that provides the correct general approach, and then extend it to provide the necessary details of the solution.

For example, if you wished to display a java generated web page, you would use a J2EE HttpServlet component, which would ensure it would fit into a J2EE Servlet Container that would handle all the plumbing of receiving HTTP requests, decomposing them into Java Objects and method calls, directing them to the correct container, gathering the output from the correct handler via the container, composing the output into HTTP responses, etc.
