<%@tag description="language toggler" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul class="list-unstyled pull-left">
    <li>
        <form class="languageToggle"
              style="margin: 0;" action='<c:url value="/changeLocale"/>' method="post">
            <select autocomplete="off" id="language" name="language" onchange="submit()">
                <option value="en" ${locale == 'en_US' || locale=='en' ? 'selected' : ''}>English
                </option>
                <option value="ru" ${locale == 'ru_RU' || locale=='ru' ? 'selected' : ''}>Русский
                </option>
                <%--<option value="es" ${language == 'es' ? 'selected' : ''}>Español</option>--%>
            </select>
        </form>
    </li>
</ul>