function carregarFilmes(){
    const dados=document.getElementById("dados_filme")
    const endpoint="http://localhost:8080/apis/list-movies"
    fetch(endpoint).then(resp=>resp.json())
                   .then(json=>{
                       let linhas="";
                       for (let movie of json)
                          linhas+=`<tr><td>${movie.titulo}</td><td>${movie.ano}</td><td>${movie.genero}</td></tr>`
                       dados.innerHTML=linhas
                    })
                   .catch(error=>dados.innerHTML=error)
}   