const jwt = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyLCJyb2xlIjoiUk9MRV9BRE1JTiJ9.grruzTbuJJXrXjuGwCOrPKATTKeYC_qiu1cmcaaqHOM';

export default function authenticationHeader() {
  return {
    Authorization: `Bearer ${jwt}`,
  };
}
