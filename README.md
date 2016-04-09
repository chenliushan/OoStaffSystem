# OoStaffSystem
This is a demo system for OO course 
It initialize with one director and one HR staff.
They will be created automatically when this program is launch.
*This system will not connect to the database.*
**The .jar in this home dir can be executed directly**

The default Personnel for login:
director account is:
 ID = 1;
 PASSWORD = "Jana";
 
HR account is:
 ID = 2;
 PASSWORD = "Jack";

## Brief intro
**The source code dir is: src/main/java**

In this System, 
Personnel is the parent of the Staff and Director
HR extend Staff.
A constraint that ensure all the staff have to create with a not null supervisor is implemented.
Only Director has no supervisor (null).
The singleton **PersonnelProcess** is maintaining a list of temporary personnel object.

The chain of responsibility is implemented in the abstract *Handler*.
The *Personnel* extend the *Handler*, so all the *Personnel* is a *Handler*.
And all of Handler can handle the LeavingApplication, 
while the handle methods are different among different types of personnel.

## Function list:
* [**Login**](#login-personnel)(Personnel)
* [**Handle Leaving application request**](#handle-leaving-application-request-personnel)(Personnel)
* [**Apply a leave**](#apply-a-leave-staff)(Staff)
* [**View my application Results**](#view-my-application-results-staff)(Staff)
* [**Create new staff**](#create-new-staff-hrstaff)(HrStaff)
* [**Delete a staff**](#delete-a-staff-hrstaff)(HrStaff)
* [**View all staff information**](#view-all-staff-information-director)(Director)


## Function description:

#### Login (Personnel)
(Personnel)
With the Personnel ID and password.
At the first time of login, the system will create a singleton PersonnelProcess which maintain a list of *Personnel*.

#### Handle Leaving application request (Personnel)
This function is defined and implemented in the abstract Handler 
_Every Handler keeps a list of LeavingApplication that should be handle by he/she._
There are two handle methods: **Endorse** and **Decline**
- **Decline**: Will set the application result as Fail and notify the applicant. 
- **Endorse**: Will set the application result as Success and notify the supervisor (if supervisor is null, it will notify the applicant).
The *Handler* can only choose one of the methods to handle the *LeavingApplication*.
View [Handler.java](https://github.com/chenliushan/OoStaffSystem/blob/master/src/main/java/model/Handler.java)

#### Apply a leave (Staff)
This function is defined and implemented in the Staff (Director do not have this function).
The _LeavingApplication_ will be pass to the staff's supervisor (cannot be null) automatically.
_Constraint: The input must match the yyyy-MM-dd format and the end date must after the start date._

#### View my application Results (Staff)
A staff can be notify(inform) by receive a LeavingApplication with handle result.
_Every Staff keeps a list of LeavingApplication results that are treated._

#### Create new staff (HrStaff)
The new staff should be assign a supervisor while he/she is created.
_The legality of the supervisor will be checked before assignment._

#### Delete a staff (HrStaff)
The default personnel(Director and HR) cannot be delete.
The staff cannot be delete if he/she is other staff's supervisor.
_The legality of the deletablity will be checked before delete operation._

#### View all staff information (Director)
View the list of _Personnel_ in the singleton _PersonnelProcess_.