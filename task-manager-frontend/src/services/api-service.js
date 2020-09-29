import axios from 'axios';
import authenticationHeader from './api-headers';

const baseUrl = 'http://localhost:8080/api';
export const apiClient = axios.create({
  baseURL: baseUrl,
});
export default {
  fetchIssues() {
    return apiClient.get('/column', { headers: authenticationHeader() });
  },
  updateColumn(column) {
    return apiClient.put('/column', column, { headers: authenticationHeader() });
  },
  deleteIssue(issueId) {
    return apiClient.delete(`/issue/${issueId}`, { headers: authenticationHeader() });
  },
  createIssue(issue) {
    return apiClient.post('/issue', issue, { headers: authenticationHeader() });
  },
  moveIssue(issue) {
    return apiClient.put('/issue/move', issue, { headers: authenticationHeader() });
  },
};
