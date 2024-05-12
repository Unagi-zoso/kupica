const csrf_token = document.querySelector('meta[name="_csrf"]').content;
const csrf_header = document.querySelector('meta[name="_csrf_header"]').content;
const logoutBtn = document.getElementById('logout__btn');


logoutBtn.addEventListener('click', () => {
  const jsonHeaders = {
    'Content-Type': 'application/json'
  };
  jsonHeaders[csrf_header] = csrf_token;

  fetch('/logout', {
    method: 'POST',
    headers: jsonHeaders,
  }).then(response => {
    if (response.ok) {
      alert('로그아웃 되었습니다.');
      window.location.href = '/';
    }
  });
});
