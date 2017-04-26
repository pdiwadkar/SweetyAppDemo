<html>
<head>
  <title>${title}
</head>
<body form>
  <h1>${title}</h1>

  <p>${car.make} by ${car.model}</p>

  <ul>
    <#list cars as car>
      <li>${car_index + 1}. ${car.make} from ${car.model}</li>
    </#list>
  </ul>
<input type = submit>Click Here</input>
</body>
</html>
