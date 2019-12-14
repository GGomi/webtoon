import React from 'react';

function Index () {
  const profile = JSON.parse(sessionStorage.getItem('auth'));
  console.log(profile);
  return (
      <div>
        {"hi hi"}
      </div>
  );

}

export default Index;