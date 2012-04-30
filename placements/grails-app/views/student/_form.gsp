<%@ page import="placements.Student" %>



<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="student.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${studentInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'studentID', 'error')} ">
	<label for="studentID">
		<g:message code="student.studentID.label" default="Student ID" />
		
	</label>
	<g:textField name="studentID" value="${studentInstance?.studentID}"/>
</div>

