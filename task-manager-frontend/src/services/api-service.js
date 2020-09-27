import axios from 'axios';

const baseUrl = 'http://localhost:8080/';
const apiClient = axios.create({
  baseURL: baseUrl,
});

export default {
  fetchIssues() {
    return apiClient.get('column');
  },
  updateColumn(column) {
    return apiClient.put('/column', column);
  },
  deleteIssue(issueId) {
    return apiClient.delete(`/issue/${issueId}`);
  },
  createIssue(issue) {
    console.log(issue);
    return apiClient.post('/issue', issue);
  },
};
