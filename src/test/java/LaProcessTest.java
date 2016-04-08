import model.Staff;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import process.LaProcess;
import process.PersonnelProcess;

import java.util.Calendar;
import java.util.Date;

/** 
* LaProcess Tester. 
* 
* @author <Authors name> 
* @since <pre>四月 7, 2016</pre> 
* @version 1.0 
*/ 
public class LaProcessTest { 

@Before
public void before() throws Exception {
    Staff s=new Staff("aa","aa",1000);
    PersonnelProcess.getInstance().addPersonnel(s);
    Date sd= new Date(2016,11,03);
    Date ed= new Date(2016,11,06);
    LaProcess.getLaProcess().createLa(s,sd,ed,"afdasfadsf");
    Staff hr=(Staff) PersonnelProcess.getInstance().searchById(2);
    LaProcess.getLaProcess().createLa(hr,sd,ed,"hrhrhr");
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getLaProcess() 
* 
*/ 
@Test
public void testGetLaProcess() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: searchById(int id) 
* 
*/ 
@Test
public void testSearchById() throws Exception {
//TODO: Test goes here...
} 

/** 
* 
* Method: createLa(Staff s, Date startDate, Date endDate, String note) 
* 
*/ 
@Test
public void testCreateLa() throws Exception {
    Staff s=new Staff("aa","aa",1000);
    PersonnelProcess.getInstance().addPersonnel(s);
    Date sd= new Date(2016,11,03);
    Date ed= new Date(2016,11,06);
    assert LaProcess.getLaProcess().createLa(s,sd,ed,"afdasfadsf")==null;
//TODO: Test goes here... 
} 

/** 
* 
* Method: getMyAllApplication(int id) 
* 
*/ 
@Test
public void testGetMyAllApplication() throws Exception {
    assert LaProcess.getLaProcess().getApplicationIShouldHandle(1)!=null;

//TODO: Test goes here... 
} 

/** 
* 
* Method: getMyAllApplicationWithStatus(int id, int status) 
* 
*/ 
@Test
public void testGetMyAllApplicationWithStatus() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getApplicationIShouldHandle(int id) 
* 
*/ 
@Test
public void testGetApplicationIShouldHandle() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getApplicationIHaveHandle(int id) 
* 
*/ 
@Test
public void testGetApplicationIHaveHandle() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: printInfo() 
* 
*/ 
@Test
public void testPrintInfo() throws Exception { 
//TODO: Test goes here... 
} 


/** 
* 
* Method: getNextId() 
* 
*/ 
@Test
public void testGetNextId() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = LaProcess.getClass().getMethod("getNextId"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

} 
