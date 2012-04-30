<%@ page import="placements.Student" %>



<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'studentID', 'error')} ">
	<label for="studentID">
		<g:message code="student.studentID.label" default="Student ID" />
		
	</label>
	<g:textField name="studentID" value="${studentInstance?.studentID}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'studentName', 'error')} ">
	<label for="studentName">
		<g:message code="student.studentName.label" default="Student Name" />
		
	</label>
	<g:textField name="studentName" value="${studentInstance?.studentName}"/>
</div>

