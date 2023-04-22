<!DOCTYPE html>
<html lang="en">
<#-- @ftlvariable name="data" type="io.qameta.allure.attachment.http.HttpRequestAttachment" -->
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
    <link type="text/css" href="https://yandex.st/highlightjs/8.0/styles/github.min.css" rel="stylesheet"/>
    <script type="text/javascript" src="https://yandex.st/highlightjs/8.0/highlight.min.js"></script>
    <script type="text/javascript" src="https://yandex.st/highlightjs/8.0/languages/bash.min.js"></script>
    <script type="text/javascript" src="https://yandex.st/highlightjs/8.0/languages/json.min.js"></script>
    <script type="text/javascript" src="https://yandex.st/highlightjs/8.0/languages/xml.min.js"></script>
    <script type="text/javascript">hljs.initHighlightingOnLoad();</script>

    <style>
        pre {
            white-space: pre-wrap;
        }
    </style>
    <title>Request</title>
</head>
<body>
<div>
    <pre><code><#if data.method??>${data.method}<#else>Unknown</#if>: <#if data.url??>${data.url}<#else>Unknown</#if></code></pre>
</div>

<#if data.body??>
    <h4>Body</h4>
    <div>
    <pre><code>${data.body}</code></pre>
    </div><#else><h4>Body</h4>
    <div>
        <pre><code>NONE</code></pre>
    </div>
</#if>

<#if (data.headers)?has_content>
    <h4>Headers</h4>
    <div><pre><code><#list data.headers as name, value><strong>${name}</strong>: ${value}
</#list></code></pre>
    </div>
</#if>


<#if (data.cookies)?has_content>
    <h4>Cookies</h4>
    <div><pre><code><#list data.cookies as name, value><strong>${name}</strong>: ${value}
</#list></code></pre>
    </div>
</#if>

<#if data.curl??>
    <h4>Curl</h4>
    <div>
        <pre><code>${data.curl}</code></pre>
    </div>
</#if>
</body>
</html>