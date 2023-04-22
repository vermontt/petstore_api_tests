<!DOCTYPE html>
<html lang="en">
<#-- @ftlvariable name="data" type="io.qameta.allure.attachment.http.HttpResponseAttachment" -->
<head>
    <meta http-equiv="content-type" content="text/html; charset = UTF-8">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.4.0/styles/default.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.4.0/highlight.min.js"></script>
    <script type="text/javascript">hljs.highlightAll();</script>

    <style>
        pre {
            white-space: pre-wrap;
        }
    </style>
    <title>Response</title>
</head>
<body>
<div><h4>Status code</h4> <#if data.responseCode??>
        <pre><code><strong>${data.responseCode}</strong></code></pre>
    <#else>Unknown</#if></div>
<#if data.url??>

    <div>
    <pre><code>${data.url}</code></pre>
    </div></#if>

<#if (data.headers)?has_content>
    <h4>Headers</h4>
    <div><pre><code><#list data.headers as name, value><strong>${name}</strong>: ${value}
</#list></code></pre>
    </div>
</#if>

<#if data.body??>
    <h4>Body</h4>
    <div>
    <pre><code>${data.body}</code></pre>
    </div><#else><h4>Body</h4>
    <div>
        <pre><code>NONE</code></pre>
    </div>
</#if>

<#if (data.cookies)?has_content>
    <h4>Cookies</h4>
    <div><pre><code><#list data.cookies as name, value><strong>${name}</strong>: ${value}
</#list></code></pre>
    </div>
</#if>
</body>
</html>