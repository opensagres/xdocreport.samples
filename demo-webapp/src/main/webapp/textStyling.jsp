<%--

                       GNU LESSER GENERAL PUBLIC LICENSE
                           Version 3, 29 June 2007

     Copyright (C) 2007 Free Software Foundation, Inc. <http://fsf.org/>
     Everyone is permitted to copy and distribute verbatim copies
     of this license document, but changing it is not allowed.


      This version of the GNU Lesser General Public License incorporates
    the terms and conditions of version 3 of the GNU General Public
    License, supplemented by the additional permissions listed below.

      0. Additional Definitions.

      As used herein, "this License" refers to version 3 of the GNU Lesser
    General Public License, and the "GNU GPL" refers to version 3 of the GNU
    General Public License.

      "The Library" refers to a covered work governed by this License,
    other than an Application or a Combined Work as defined below.

      An "Application" is any work that makes use of an interface provided
    by the Library, but which is not otherwise based on the Library.
    Defining a subclass of a class defined by the Library is deemed a mode
    of using an interface provided by the Library.

      A "Combined Work" is a work produced by combining or linking an
    Application with the Library.  The particular version of the Library
    with which the Combined Work was made is also called the "Linked
    Version".

      The "Minimal Corresponding Source" for a Combined Work means the
    Corresponding Source for the Combined Work, excluding any source code
    for portions of the Combined Work that, considered in isolation, are
    based on the Application, and not on the Linked Version.

      The "Corresponding Application Code" for a Combined Work means the
    object code and/or source code for the Application, including any data
    and utility programs needed for reproducing the Combined Work from the
    Application, but excluding the System Libraries of the Combined Work.

      1. Exception to Section 3 of the GNU GPL.

      You may convey a covered work under sections 3 and 4 of this License
    without being bound by section 3 of the GNU GPL.

      2. Conveying Modified Versions.

      If you modify a copy of the Library, and, in your modifications, a
    facility refers to a function or data to be supplied by an Application
    that uses the facility (other than as an argument passed when the
    facility is invoked), then you may convey a copy of the modified
    version:

       a) under this License, provided that you make a good faith effort to
       ensure that, in the event an Application does not supply the
       function or data, the facility still operates, and performs
       whatever part of its purpose remains meaningful, or

       b) under the GNU GPL, with none of the additional permissions of
       this License applicable to that copy.

      3. Object Code Incorporating Material from Library Header Files.

      The object code form of an Application may incorporate material from
    a header file that is part of the Library.  You may convey such object
    code under terms of your choice, provided that, if the incorporated
    material is not limited to numerical parameters, data structure
    layouts and accessors, or small macros, inline functions and templates
    (ten or fewer lines in length), you do both of the following:

       a) Give prominent notice with each copy of the object code that the
       Library is used in it and that the Library and its use are
       covered by this License.

       b) Accompany the object code with a copy of the GNU GPL and this license
       document.

      4. Combined Works.

      You may convey a Combined Work under terms of your choice that,
    taken together, effectively do not restrict modification of the
    portions of the Library contained in the Combined Work and reverse
    engineering for debugging such modifications, if you also do each of
    the following:

       a) Give prominent notice with each copy of the Combined Work that
       the Library is used in it and that the Library and its use are
       covered by this License.

       b) Accompany the Combined Work with a copy of the GNU GPL and this license
       document.

       c) For a Combined Work that displays copyright notices during
       execution, include the copyright notice for the Library among
       these notices, as well as a reference directing the user to the
       copies of the GNU GPL and this license document.

       d) Do one of the following:

           0) Convey the Minimal Corresponding Source under the terms of this
           License, and the Corresponding Application Code in a form
           suitable for, and under terms that permit, the user to
           recombine or relink the Application with a modified version of
           the Linked Version to produce a modified Combined Work, in the
           manner specified by section 6 of the GNU GPL for conveying
           Corresponding Source.

           1) Use a suitable shared library mechanism for linking with the
           Library.  A suitable mechanism is one that (a) uses at run time
           a copy of the Library already present on the user's computer
           system, and (b) will operate properly with a modified version
           of the Library that is interface-compatible with the Linked
           Version.

       e) Provide Installation Information, but only if you would otherwise
       be required to provide such information under section 6 of the
       GNU GPL, and only to the extent that such information is
       necessary to install and execute a modified version of the
       Combined Work produced by recombining or relinking the
       Application with a modified version of the Linked Version. (If
       you use option 4d0, the Installation Information must accompany
       the Minimal Corresponding Source and Corresponding Application
       Code. If you use option 4d1, you must provide the Installation
       Information in the manner specified by section 6 of the GNU GPL
       for conveying Corresponding Source.)

      5. Combined Libraries.

      You may place library facilities that are a work based on the
    Library side by side in a single library together with other library
    facilities that are not Applications and are not covered by this
    License, and convey such a combined library under terms of your
    choice, if you do both of the following:

       a) Accompany the combined library with a copy of the same work based
       on the Library, uncombined with any other library facilities,
       conveyed under the terms of this License.

       b) Give prominent notice with the combined library that part of it
       is a work based on the Library, and explaining where to find the
       accompanying uncombined form of the same work.

      6. Revised Versions of the GNU Lesser General Public License.

      The Free Software Foundation may publish revised and/or new versions
    of the GNU Lesser General Public License from time to time. Such new
    versions will be similar in spirit to the present version, but may
    differ in detail to address new problems or concerns.

      Each version is given a distinguishing version number. If the
    Library as you received it specifies that a certain numbered version
    of the GNU Lesser General Public License "or any later version"
    applies to it, you have the option of following the terms and
    conditions either of that published version or of any later version
    published by the Free Software Foundation. If the Library as you
    received it does not specify a version number of the GNU Lesser
    General Public License, you may choose any version of the GNU Lesser
    General Public License ever published by the Free Software Foundation.

      If the Library as you received it specifies that a proxy can decide
    whether future versions of the GNU Lesser General Public License shall
    apply, that proxy's public statement of acceptance of any version is
    permanent authorization for you to choose that version for the
    Library.

--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="fr.opensagres.xdocreport.webapp.utils.HTMLUtils"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>XDocReport - Text styling with HTML syntax</title>
</head>
<link href="styles/xdocreport.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="scripts/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
	window.onload = function()
	{
		CKEDITOR.replace( 'editor1',
	    {
	        toolbar : 'MyToolbar',
	        uiColor : '#9AB8F3'
	    });
	};
	function select(select) {
		if (select.value == "odt") {
			document.getElementById('odtExplanation').style.display = 'block';
			document.getElementById('docxExplanation').style.display = 'none';
		}
		else {
			document.getElementById('odtExplanation').style.display = 'none';
			document.getElementById('docxExplanation').style.display = 'block';
		}
	};
</script>
<body>

<a href="<%=HTMLUtils.generateIndexJSPURL(request)%>">&lt;&lt; Home</a>

<fieldset><legend>Text styling</legend>
<form name="helloWorldForm"
	action="<%=request.getContextPath()%>/textstyling"
	method="post">
<table>
	<tr>
		<td>
<table>
	<tr>
		<td>Document type : <select name="reportId" onchange="javascript:select(this)" >
								<%
								HTMLUtils.generateHTMLOption("docx", "docx", request, out, "reportId");
								HTMLUtils.generateHTMLOption("odt", "odt", request, out, "reportId");
								%>								
							</select>
	</tr>
	<tr>
		<td>
			<fieldset>
				<legend>Data model (used in Text Styling)</legend>
				<table>
					<tr>
						<td>Project ($project):</td>
						<td><input name="project" value="XDocReport" size="50" /></td>
					</tr>
					<tr>
						<td>URL ($url):</td>
						<td><input name="url" value="http://code.google.com/p/xdocreport/" size="50" /></td>
					</tr>
			</fieldset>
		</td>
	</tr>
	<tr>
		<td valign="top" >HTML Comments :</td>
		<td>
<textarea id="editor1" name="comments_html">

Here are severals styles : 
&lt;ul&gt;
	&lt;li&gt;&lt;strong&gt;Bold&lt;/strong&gt; style.&lt;/li&gt;
	&lt;li&gt;&lt;em&gt;Italic&lt;/em&gt; style.&lt;/li&gt;
	&lt;li&gt;&lt;strong&gt;&lt;em&gt;BoldAndItalic&lt;/em&gt;&lt;/strong&gt; style.&lt;/li&gt;
	&lt;li&gt;&lt;u&gt;Underline&lt;/u&gt; style.&lt;/li&gt;
	&lt;li&gt;&lt;strike&gt;Strike&lt;/strike&gt; style.&lt;/li&gt;
&lt;/ul&gt;

Here are 3 styles : 

&lt;ol&gt;
	&lt;li&gt;&lt;strong&gt;Bold&lt;/strong&gt; style.&lt;/li&gt;
	&lt;li&gt;&lt;em&gt;Italic&lt;/em&gt; style.&lt;/li&gt;
	&lt;li&gt;&lt;strong&gt;&lt;em&gt;BoldAndItalic&lt;/em&gt;&lt;/strong&gt; style.&lt;/li&gt;
&lt;/ol&gt;

Here you can use Velocity syntax like &lt;a href="$url"&gt;$project&lt/a&gt;

&lt;p&gt;
&lt;a href="http://code.google.com/p/xdocreport/"&gt;XDocReport&lt/a&gt; can manage those styles. Now some &lt;strong&gt;headers&lt;/strong&gt;:
&lt;/p&gt;

&lt;h1&gt;Title 1&lt;/h1&gt;
Some text...

&lt;h2&gt;Title 2&lt;/h2&gt;
Some text...

&lt;h3&gt;Title 3&lt;/h3&gt;
Some text...

&lt;table border="1" cellpadding="1" cellspacing="1" style="width: 500px;"&gt;
	&lt;tbody&gt;
		&lt;tr&gt;
			&lt;td&gt;
				A&lt;/td&gt;
			&lt;td&gt;
				B&lt;/td&gt;
		&lt;/tr&gt;
		&lt;tr&gt;
			&lt;td&gt;
				C&lt;/td&gt;
			&lt;td&gt;
				D&lt;/td&gt;
		&lt;/tr&gt;
		&lt;tr&gt;
			&lt;td&gt;
				E&lt;/td&gt;
			&lt;td&gt;
				F&lt;/td&gt;
		&lt;/tr&gt;
	&lt;/tbody&gt;
&lt;/table&gt;
</textarea>
		</td>
	</tr>
	<tr>
		<td valign="top" >Google Wiki Comments :</td>
		<td>
<textarea name="comments_gwiki" style="height:200px" >Here are severals styles : 
 * *Bold* style.
 * _Italic_ style.
 * _*BoldAndItalic*_ style.

Here are 3 styles : 

 # *Bold* style.
 # _Italic_ style.
 # _*BoldAndItalic*_ style.

XDocReport can manage those styles.
</textarea></td>
	</tr>
	<tr>
		<td valign="top" ><a href="http://meta.wikimedia.org/wiki/Wiki_text_formatting_help" >MediaWiki</a> Comments :</td>
		<td>
<textarea name="comments_mediawiki" style="height:200px" >Here are severals styles : 
* '''Bold''' style.
* ''Italic'' style.
* '''''BoldAndItalic''''' style.

Here are 3 styles : 

# '''Bold''' style.
# ''Italic'' style.
# '''''BoldAndItalic''''' style.

Here you can use Velocity syntax like [$url $project]

[http://code.google.com/p/xdocreport/ XDocReport] can manage those styles.
</textarea></td>		
	</tr>	
	<tr>
		<td colspan="2"><input type="submit" value="Generate Report"></td>
	</tr>
</table>
		</td>
		<td valign="top" >
			<% boolean odtExplanation = "odt".equals(request.getParameter("reportId"));%>
			<div id="docxExplanation" <% if(odtExplanation) {%> style="display:none;"<%}%>>
				<fieldset>
					<legend>Explanation</legend>
					<p>
					In this sample text styling, the following docx document is used as "template" report :
					</p>
					<p>
					<img src="images/DocxTextStyling.png" />
					</p>
					<p>
						This docx document contains 3 merge fields which will be replaced with styled text :
						<ul>
							<li><b>$comments_html</b> will be replaced by HTML content from the "HTML Comments" textarea.
							</li>
							<li><b>$comments_gwiki</b> will be replaced by Google Wiki content from the "Google Wiki Comments " textarea.
							</li>
							<li><b>$comments_mediawiki</b> will be replaced by MediaWiki content from the "MediaWiki Comments" textarea.
							</li>
						</ul>
					</p>
					<p>
						Fill each text area and click on <input type="submit" value="Generate Report"> to generate the report.
					</p>
					<p>
						Please see <a href="http://code.google.com/p/xdocreport/wiki/DocxReportingJavaMainTextStyling" >XDocReport Wiki</a> for more information.
					</p>					
				</fieldset>
			</div>
			<div id="odtExplanation" <% if(!odtExplanation) {%> style="display:none;"<%}%> >
				<fieldset>
					<legend>Explanation</legend>
					<p>
					In this sample text styling, the following odt document is used as "template" report :
					</p>
					<p>
					<img src="images/ODTTextStyling.png" />
					</p>
					<p>
						This odt document contains 3 input fields which will be replaced with styled text :
						<ul>
							<li><b>$comments_html</b> will be replaced by HTML content from the "HTML Comments" textarea.
							</li>
							<li><b>$comments_gwiki</b> will be replaced by Google Wiki content from the "Google Wiki Comments " textarea.
							</li>
							<li><b>$comments_mediawiki</b> will be replaced by MediaWiki content from the "MediaWiki Comments" textarea.
							</li>
						</ul>
					</p>
					<p>
						Fill each text area and click on <input type="submit" value="Generate Report"> to generate the report.
					</p>
					<p>
						Please see <a href="http://code.google.com/p/xdocreport/wiki/ODTReportingJavaMainTextStyling" >XDocReport Wiki</a> for more information.
					</p>
				</fieldset>
			</div>
		</td>
	</tr>
</table>
</form>

</body>
</html>