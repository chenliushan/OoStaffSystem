# OoStaffSystem
This is a demo system for OO course and it will not connect to the database.
This system only allow one director and one HR staff, and they will be created automatically when this program is launch.

The default director and HR is:


In this System, Personnel is the parent of the Staff and Director
HR extend Staff
A singleton Personnel process is maintaining a list of temporary personnel object.

The chain of responsibility is implemented in the MyChainHandler
And the Personnel extend the MyChainHandler, so all the personnel is a Handler
all of them can handle the LeavingApplication, while the handle methods are different among different types of personnel.
